import axios from 'axios'

const hashRainbowTable = (hash, tipo) => axios.post('/hash/rainbowtable', { hash, tipo }).then(p => p.data)

export const adminService = {
    hashRainbowTable
}