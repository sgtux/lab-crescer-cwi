import React, { useEffect, useState, useCallback } from 'react'
import { useDispatch } from 'react-redux'

import { Container, Title } from './styles'

import { userChanged } from '../../store/actions'

import { usuarioService } from '../../services'

import { Login } from './Login'
import { Create } from './Create'


export function Auth() {

    const [isLogin, setIsLogin] = useState(true)
    const dispatch = useDispatch()

    const init = useCallback(async () => {
        try {
            const data = await usuarioService.getUserLogado()
            dispatch(userChanged(data))
        } catch (e) {
            console.log(e)
        }
    }, [dispatch])

    useEffect(() => init(), [init])

    return (
        <Container>
            <div style={{ display: 'flex', flexDirection: 'row' }}>
                <div style={{ flex: 1, textAlign: 'left' }}>
                    <Title>Damn <span style={{ color: '#c34731' }}>Vulnerable</span> Social Network</Title>
                    <span style={{ fontSize: 30 }}>Uma aplicação vulnerável indicada para testes e entendimento de conceitos de segurança em Aplicações WEB</span>
                </div>
                <div style={{ flex: 1 }}>
                    {isLogin ? <Login onChangeMode={() => setIsLogin(false)} /> : <Create onChangeMode={() => setIsLogin(true)} />}
                </div>
            </div>
        </Container>
    )
}