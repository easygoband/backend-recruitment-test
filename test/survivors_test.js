let server = require('../server')
let chai = require('chai')
let chaiHttp = require('chai-http')

chai.should()
chai.use(chaiHttp)

describe('Survivor API', ()=>{
    describe('test GET routes /survivors',()=>{
        it('It should return all survivors and 200',(done)=>{
            chai.request(server)
                .get('/survivors/')
                .end((err,response)=>{
                        response.should.have.status(200)
                    done()
                })
        })

        it('It should return survivor by ID 1',(done)=>{
            const id = 1
            chai.request(server)
                .get('/survivors/'+id)
                .end((err,response)=>{
                    response.should.have.status(200)
                    done()
                })
        })

        it('It should post survivor',(done)=>{
            chai.request(server)
                .post('/survivors/new')
                .send({
                    name: "Francisco Gonzalez",
                    age: 26,
                    gender: "M",
                    lat: 0.0,
                    lng: 0.0,
                    seenCount:0,
                    inventory: [
                        {item_id:1,quantity:5},
                        {item_id:2,quantity:3}
                    ]
                })
                .end((err,response)=>{
                    response.should.have.status(201)
                    done()
                })
        })
    })
})