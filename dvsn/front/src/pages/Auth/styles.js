import styled from 'styled-components'

export const Container = styled.div`
    height: 400px;
    margin: 0 auto;
    margin-top: 60px;
    padding: 30px 100px;
    text-align: center;
`

export const Title = styled.div`
    font-weight: bold;
    font-size: 50px;
    color: #1877f2;                    
                    
`

export const TextInput = styled.input`
    border: 1px solid #dddfe2;
    color: #1d2129;
    font-family: Helvetica, Arial, sans-serif;
    font-size: 12px;
    height: 50px;
    line-height: 16px;
    vertical-align: middle;
    border-radius: 6px;
    font-size: 20px;
    margin-bottom: 20px;
    padding-left: 6px;
    width: 100%;
`

export const FormContainer = styled.div`
    border-radius: 10px;
    width: 400px;
    background-color: white;
    padding: 20px;
    margin-left: 50px;
    text-align: center;
    overflow: hidden;
`

export const Button = styled.button`
    background-color: #1877f2;
    width: 100%;
    border: none;
    border-radius: 6px;
    color: white;
    height: 50px;
    font-size: 30px;
    transition: 200ms;
    &:hover {
        opacity: .8;
        cursor: pointer;
    }
`

export const ChangeScreenButton = styled(Button)`
    background-color: #42b72a;
`

export const Line = styled.div`
    border-top: 1px solid #ccc;
    margin-top: 30px;
    margin-bottom: 20px;
`

export const ErrorMessage = styled.div`
    margin-top: 20px;
    color: red;
`