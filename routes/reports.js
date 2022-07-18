const express = require('express')
const router = express.Router()

const mysql = require('mysql')

const con = mysql.createConnection({
    host : 'localhost',
    user : 'root',
    password : '',
    database : 'zssn'
})

con.connect((error)=>{
    if(error) throw error
})

router.get('/survivors',(req,res)=>{
    const sql = 'select count(*) as survivors from survivor where infected = false union select count(*) as nonsurvivors from survivor where infected = true'

    con.query(sql,(err,results)=>{
        if (err) throw err

        let total = results[0].survivors + results[1].survivors
        let per = (results[0].survivors*100) / total

        res.status(201).send({
            data : per,
            message : "% of survivors",
            error : false
        })
    })
})

router.get('/nonsurvivors',(req,res)=>{
    const sql = 'select count(*) as survivors from survivor where infected = false union select count(*) as nonsurvivors from survivor where infected = true'

    con.query(sql,(err,results)=>{
        if (err) throw err

        let total = results[0].survivors + results[1].survivors
        let per = (results[1].survivors*100) / total

        res.status(201).send({
            data : per,
            message : "% of non-survivors",
            error : false
        })
    })
})

router.get('/avgResource',(req,res)=>{
    const sql = `select avg(quantity) from inventory inner join survivor on inventory.survivor_id=survivor.id where inventory.item_id = 1 and survivor.infected = 0
    union
    select avg(quantity) from inventory inner join survivor on inventory.survivor_id=survivor.id where inventory.item_id = 2 and survivor.infected = 0
    union
    select avg(quantity) from inventory inner join survivor on inventory.survivor_id=survivor.id where inventory.item_id = 3 and survivor.infected = 0
    union
    select avg(quantity) from inventory inner join survivor on inventory.survivor_id=survivor.id where inventory.item_id = 4 and survivor.infected = 0;
    `

    con.query(sql,(err,results)=>{
        if (err) throw err

        res.status(200).send({
            data : {
                water : results[0],
                food : results[1],
                medication : results[2],
                ammunition : results[3]
            },
            message : "Item per survivor",
            error : false
        })
    })
})

router.get('/pointsLost',(req,res)=>{
    const sql = `select sum(iv.quantity) as quantity,sum(it.points) as points from inventory iv inner join item it on it.id = iv.item_id inner join survivor sur on sur.id = iv.survivor_id where sur.infected = 1`

    con.query(sql,(err,results)=>{
        if (err) throw err

        let pointsLost = results[0].quantity * results[0].points

        res.status(200).send({
            data : pointsLost,
            message : "Points lost because of infected survivor",
            error : false
        })
    })
})

module.exports = router