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

    const sql = "SELECT * FROM item"

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
                message : "item's not found :(",
                error : true
            })
        }
    })

})

module.exports = router