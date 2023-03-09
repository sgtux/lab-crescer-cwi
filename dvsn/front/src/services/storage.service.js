import { StorageKeys } from '../utils'

const setCurrentMenu = menu => localStorage.setItem(StorageKeys.CURRENT_MENU, menu)

const getCurrentMenu = () => localStorage.getItem(StorageKeys.CURRENT_MENU)

const setToken = token => localStorage.setItem(StorageKeys.TOKEN, token)

const getToken = () => localStorage.getItem(StorageKeys.TOKEN)

const setUser = user => user ? localStorage.setItem(StorageKeys.USER, JSON.stringify(user)) : localStorage.removeItem(StorageKeys.USER)

const getUser = () => JSON.parse(localStorage.getItem(StorageKeys.USER))

export const storageService = {
    setCurrentMenu,
    getCurrentMenu,
    setToken,
    getToken,
    getUser,
    setUser
}