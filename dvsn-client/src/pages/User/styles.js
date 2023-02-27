import styled from 'styled-components'

export const Container = styled.div`
    margin: 0 auto;
    width: 400px;
    margin-top: 60px;
`

export const UserBox = styled.div`
    margin-top: 20px;
    border-radius: 20px;
    height: 100px;
    box-shadow: 1px 1px 3px 0px #bbb;
    display: flex;
    flex-direction: row;
    background-color: white;
    padding: 4px;
`

export const UserImage = styled.img`
    width: 100px;
    border-radius: 20px;
`

export const SearchInput = styled.input`
    background-color: #fff;
    border: none;
    border-radius: 20px 0 0 20px;
    height: 40px;
    padding: 0 20px;
    font-size: 16px;
    margin-top: 4px;
    transition: 200ms;
    &:focus {
        outline: 0;
        box-shadow: 0 0 2px 2px #ddd;
    }
`

export const SearchBtn = styled.button`
    height: 40px;
    border-radius: 0 20px 20px 0;
    border: none;
    transition: 200ms;
    font-size: 16px;
    padding: 6px 12px;
    text-transform: uppercase;
    &:hover {
        cursor: pointer;
        box-shadow: 0 0 1px 1px #ddd;
        background-color: #ddd;
    }
`