import React from 'react'
import { useSelector } from 'react-redux'
import { App } from '../App/App'
import { Auth } from '../../pages'

export function RootComponent() {
    const user = useSelector(state => state.appState.user)
    return user ? <App /> : <Auth />
}