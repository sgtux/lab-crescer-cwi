import React from 'react'
import { useSelector, useDispatch } from 'react-redux'
import { userChanged, menuChanged } from '../../store/actions'
import { usuarioService } from '../../services'
import { MenuStates } from '../../utils'

import { BtnLogout, PostProfileImage, Container, MenuProfile, ContainerMenu, Line, SearchInput, ActionBox, BtnMenu } from './styles'

export function Toolbar() {

    const { user, menu } = useSelector(state => state.appState)

    const dispatch = useDispatch()

    function logout() {
        usuarioService.logout()
            .then(() => dispatch(userChanged(null)))
    }

    function menuAlterado(novoMenu) {
        if (novoMenu !== menu)
            dispatch(menuChanged(novoMenu))
    }

    return (
        <Container>
            <img src="favicon.ico" height={50} />
            <ActionBox>
                <BtnMenu onClick={() => menuAlterado(MenuStates.USUARIOS)} selected={menu === MenuStates.USUARIOS}>Usuários</BtnMenu>
                <BtnMenu onClick={() => menuAlterado(MenuStates.POSTS)} selected={menu === MenuStates.POSTS}>Posts</BtnMenu>
            </ActionBox>
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