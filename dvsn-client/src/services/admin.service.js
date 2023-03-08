import axios from 'axios'

const hashRainbowTable = (hash, tipo) => axios.post('/admin/rainbowtable', { hash, tipo }).then(p => p.data)

export const adminService = {
    hashRainbowTable
}