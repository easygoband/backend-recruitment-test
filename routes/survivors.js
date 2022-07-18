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

router.get('/',(req,res)=>{

    const sql = "SELECT * FROM survivor"

    con.query(sql,(err,results)=>{
        if (err) throw err
        if (results.length > 0){
            res.status(200).send({
                data : results,
                message : "OK",
                error : false
            })
        } else {
            res.status(404).send({
                message : "Error",
                error : true
            })
        }
    })
})

router.get('/:id',(req,res) => {
    const sql = "SELECT * FROM survivor WHERE id = "+req.params.id

    con.query(sql,(err,results)=>{
        if (err) throw err
        if (results.length > 0){

            res.status(200).send({
                data : {
                    survivor : results
                },
                message : "OK",
                error : false
            })
        } else {
            res.status(404).send({
                message : "Error",
                error : true
            })
        }
    })
})

router.get('/:id/inventory',(req,res)=>{

    const sql = "SELECT * FROM inventory WHERE survivor_id = "+req.params.id

    con.query(sql,(err,results)=>{
        if (err) throw err
        res.status(200).send({
            data : {
                survivor : results
            },
            message : "OK",
            error : false
        })
    })

})

router.post('/new',(req,res) => {

    const sql = "INSERT INTO survivor SET ?"

    const survivorObj = {
        name : req.body.name,
        age : req.body.age,
        gender : req.body.gender,
        lat : req.body.lat,
        lng : req.body.lng,
        seenCount : 0
    }

    con.query(sql,survivorObj,(err,results)=>{
        if (err) throw err
        req.body.inventory.forEach(element => {
            const sql = "INSERT INTO inventory SET ?"

            const invObj = {
                survivor_id : results.insertId,
                item_id : element.item_id,
                quantity : element.quantity
            }

            con.query(sql,invObj,(err,result)=>{
                if (err) throw err
            })
        });

        res.status(201).send({
            message : "Survivor registered",
            error : false
        })
    })

})

router.put('/:id/updateLocation',(req,res) => {
    
    const sql = "SELECT * FROM survivor WHERE id = "+req.params.id

    con.query(sql,(err,results) => {
        if (err) throw err

        const sql = "UPDATE survivor SET ? WHERE id = "+req.params.id

        const last_location = {
            lat : req.body.lat,
            lng : req.body.lng
        }

        con.query(sql,last_location,(err,results) => {
            if (err) throw err

            res.status(201).send({
                message : "Location updated",
                error : false
            })
        })
    })

})

router.put('/:id/flagInf',(req,res) => {
    
    const sql = "SELECT * FROM survivor WHERE id = "+req.params.id

    con.query(sql,(err,results) => {
        if (err) throw err

        if (results[0].seenCount >= 3){
            const sql = "UPDATE survivor SET infected = 1 WHERE id = "+req.params.id

            con.query(sql,(err,results) => {
                if (err) throw err

                res.status(201).send({
                    data : results,
                    message : "Survivor officialy infected :(",
                    error : false
                })
            })
        } else {
            const sql = "UPDATE survivor SET seenCount = seenCount +1 WHERE id = "+req.params.id

            con.query(sql,(err,results) => {
                if (err) throw err

                res.status(201).send({
                    data : results,
                    message : "Survivor reported :)",
                    error : false
                })
            })
        }
    })
})

router.post('/trade',(req,res) => {
    const sql = "select * from inventory inv inner join item it on it.id = inv.item_id where inv.survivor_id in ("+req.body.survivor1_id+", "+req.body.survivor2_id+")"

    con.query(sql,(err,results) => {
        if (err) throw err

        let survivor1_total =0
        let survivor2_total =0

        req.body.trade1.forEach(ele => {
            results.forEach(element => {
                if (element.survivor_id == req.body.survivor1_id && element.item_id == ele.item_id ){
                    survivor1_total = survivor1_total + (element.points*ele.quantity)
                }
            })
        })

        req.body.trade2.forEach(ele => {
            results.forEach(element => {
                if (element.survivor_id == req.body.survivor2_id && element.item_id == ele.item_id ){
                    survivor2_total = survivor2_total + (element.points*ele.quantity)
                }
            })
        })

        if(survivor1_total==survivor2_total){

            req.body.trade1.forEach(ele => {
                results.forEach(element => {
                    if (element.survivor_id == req.body.survivor1_id && element.item_id == ele.item_id ){
                        if((element.quantity-ele.quantity)>0){
                            const sql = "UPDATE inventory SET quantity = "+(element.quantity-ele.quantity)+" WHERE id = "+element.id

                            con.query(sql,(err,results)=>{
                                if (err) throw err
                            })

                            const new_item = {
                                survivor_id : req.body.survivor2_id,
                                item_id : element.item_id,
                                quantity : (element.quantity-ele.quantity)
                            }

                            const sql2 = "INSERT INTO inventory SET ?"

                            con.query(sql2,new_item,(err,results)=>{
                                if (err) throw err
                            })
                        } else {
                            res.status(404).send({
                                message : "Somebody lack of item's :(",
                                error : true
                            })
                        }
                    }
                })
            })

            req.body.trade2.forEach(ele => {
                results.forEach(element => {
                    if (element.survivor_id == req.body.survivor2_id && element.item_id == ele.item_id ){
                        if((element.quantity-ele.quantity)>0){
                            const sql = "UPDATE inventory SET quantity = "+(element.quantity-ele.quantity)+" WHERE id = "+element.id

                            con.query(sql,(err,results)=>{
                                if (err) throw err
                            })

                            const new_item = {
                                survivor_id : req.body.survivor1_id,
                                item_id : element.item_id,
                                quantity : (element.quantity-ele.quantity)
                            }

                            const sql2 = "INSERT INTO inventory SET ?"

                            con.query(sql2,new_item,(err,results)=>{
                                if (err) throw err
                            })
                        } else {
                            res.status(404).send({
                                message : "Somebody lack of item's :(",
                                error : true
                            })
                        }
                    }
                })
            })
            
            res.status(200).send({
                message : "Trade complete :)",
                error : false
            })
        } else {
            res.status(200).send({
                message : "Total of point's are not equivalent :(",
                error : false
            })
        }
    })
})

module.exports = router