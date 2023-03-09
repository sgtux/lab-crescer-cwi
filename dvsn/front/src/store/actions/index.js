export const ActionTypes = {
    USER_CHANGED: 'USER_CHANGED',
    MENU_CHANGED: 'MENU_CHANGED',
}

export const userChanged = user => ({ type: ActionTypes.USER_CHANGED, payload: user })
export const menuChanged = menu => ({ type: ActionTypes.MENU_CHANGED, payload: menu })