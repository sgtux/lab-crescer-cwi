import { combineReducers } from 'redux'
import { appReducer } from './app.reducer'

export const Reducers = combineReducers({
    appState: appReducer
})