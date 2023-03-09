import axios from 'axios'

const hashRainbowTable = (hash, tipo) => axios.post('/admin/rainbowtable', { hash, tipo }).then(p => p.data)
const getSecurityConfig = () => axios.get('/admin/security-config').then(p => p.data)
const updateSecurityConfig = config => axios.put('/admin/security-config', config)
const resetSecurityConfig = () => axios.delete('/admin/security-config')

export const adminService = {
    hashRainbowTable,
    getSecurityConfig,
    updateSecurityConfig,
    resetSecurityConfig
}