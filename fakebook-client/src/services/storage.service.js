import { StorageKeys } from '../utils'

const setUser = user => localStorage.setItem(StorageKeys.USER, JSON.stringify(user))

const getUser = () => JSON.parse(localStorage.getItem(StorageKeys.USER))

const setToken = token => localStorage.setItem(StorageKeys.TOKEN, token)

const getToken = () => localStorage.getItem(StorageKeys.TOKEN)

export const storageService = {
    setUser,
    getUser,
    setToken,
    getToken
}