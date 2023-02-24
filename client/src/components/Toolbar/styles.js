import styled from 'styled-components'

export const Container = styled.div`
    text-align: end;
    height: 60px;
    border-bottom: solid 1px #ccc;
    padding-top: 10px;
    padding-right: 20px;
`

export const BtnLogout = styled.button`
    border: none;
    background-color: transparent;
    padding: 10px;
    width: 100%;
    font-size: 16px;
    transition: 200ms;
    &:hover {
        background-color: #eee;
        border-radius: 6px;
        cursor: pointer;
    }
`

export const PostProfileImage = styled.img`
    height: 50px;
    width: 50px;
    border-radius: 50%;
`

export const ContainerMenu = styled.span`
    visibility: visible;
    &:hover div {
        visibility: visible;
        opacity: 1;
        transition: 300px;
    }
`

export const MenuProfile = styled.div`
    padding: 10px;
    text-align: center;
    position: fixed;
    background-color: white;
    border-radius: 10px;
    width: 200px;
    right: 10px;
    top: 60px;
    visibility: hidden;
    opacity: 0;
    transition: 300ms;
    box-shadow: 0 0 9px 5px #ddd;
    &:hover {
        visibility: visible;
        opacity: 1;
    }
`

export const Line = styled.div`
    margin: 10px 0;
    border: 1px solid #dbdada;

`