import { StorageKeys, MenuStates } from '../../utils'
import { ActionTypes } from '../actions'
import { storageService } from '../../services'

const initialState = {
    user: JSON.parse(localStorage.getItem(StorageKeys.USER)),
    menu: storageService.getCurrentMenu() || MenuStates.POSTS
}

export const appReducer = (state = initialState, action) => {
    switch (action.type) {
        case ActionTypes.USER_CHANGED:
            if (action.payload)
                localStorage.setItem(StorageKeys.USER, JSON.stringify(action.payload))
            else
                localStorage.removeItem(StorageKeys.USER)
            return { ...state, user: action.payload }
        case ActionTypes.MENU_CHANGED:
            storageService.setCurrentMenu(action.payload)
            return { ...state, menu: action.payload }
        default:
            return state
    }
}