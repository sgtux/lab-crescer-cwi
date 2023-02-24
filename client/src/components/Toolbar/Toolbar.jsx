import React from 'react'
import { useSelector, useDispatch } from 'react-redux'
import { userChanged } from '../../store/actions'
import { accountService } from '../../services'

import { BtnLogout, PostProfileImage, Container, MenuProfile, ContainerMenu, Line } from './styles'

export function Toolbar() {

    const user = useSelector(state => state.appState.user)

    console.log(user)

    const dispatch = useDispatch()

    function logout() {
        accountService.logout()
            .then(() => dispatch(userChanged(null)))
    }

    return (
        <Container>
            <ContainerMenu>
                <PostProfileImage alt="" src={user.foto || '/images/profile-default.jpg'} />
                <MenuProfile>
                    <span style={{ fontWeight: 'bold' }}>{user.nomeCompleto}</span>
                    <Line />
                    <BtnLogout onClick={() => logout()}>Sair</BtnLogout>
                </MenuProfile>
            </ContainerMenu>
        </Container>
    )
}