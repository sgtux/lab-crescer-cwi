import styled from 'styled-components'

export const FooterContainer = styled.div`
    text-align: center;
    font-family: "Roboto","Helvetica","Arial",sans-serif;
    padding: 20px;
    font-size: 12px;
    color: #aaa;
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
    margin-bottom: 10px;
    &:hover {
        cursor: pointer;
        box-shadow: 0 0 1px 1px #ddd;
        background-color: #ddd;
    }
`