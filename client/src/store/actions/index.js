export const ActionTypes = {
    USER_CHANGED: 'USER_CHANGED',
}

export const userChanged = user => ({ type: ActionTypes.USER_CHANGED, payload: user })