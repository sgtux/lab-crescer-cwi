import axios from 'axios'

import { storageService } from './storage.service'

const hashRainbowTable = (hash, tipo) => axios.post('/admin/rainbowtable', { hash, tipo }, storageService.getAuthHeaders()).then(p => p.data)
const getSecurityConfig = () => axios.get('/admin/security-config', storageService.getAuthHeaders()).then(p => p.data)
const updateSecurityConfig = config => axios.put('/admin/security-config', config, storageService.getAuthHeaders())
const resetSecurityConfig = () => axios.delete('/admin/security-config', storageService.getAuthHeaders())

export const adminService = {
    hashRainbowTable,
    getSecurityConfig,
    updateSecurityConfig,
    resetSecurityConfig
}