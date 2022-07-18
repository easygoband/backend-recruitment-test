const express = require('express')
const app = express()
const PORT = 3000

app.use(express.json())

app.get('/',(req,res) => {
    res.status(200).send({
        message : "Welcome to ZSSN",
        error : false
    })
})

const survivorsRouter = require("./routes/survivors")
const itemRouter = require("./routes/items")
const reportRouter = require("./routes/reports")

app.use('/survivors',survivorsRouter)
app.use('/items',itemRouter)
app.use('/reports',reportRouter)

app.listen(PORT,()=>{
    console.log(`Running ZSSN on port ${PORT}`)
})

module.exports=app