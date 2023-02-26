import styled from 'styled-components'

export const OverlayContainer = styled.div`
    position: fixed;
    z-index: 100;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    background-color: #fffc;
`

export const PostContainer = styled.div`
    margin: 0 auto;
    margin-top: 200px;
    width: 400px;
    background-color: white;
    border-radius: 10px;
    box-shadow: 0 0 9px 5px #ddd;
    padding: 20px;
`

export const Line = styled.div`
    border-top: 1px solid #ccc;
    margin-top: 20px;
    margin-bottom: 20px;
`

export const Title = styled.div`
    text-align: center;
    font-size: 30px;

`

export const CreatePostButton = styled.button`
    background-color: #1877f2;
    border: none;
    border-radius: 6px;
    color: white;
    height: 50px;
    font-size: 20px;
    transition: 200ms;
    padding: 10px;
    width: 48%;
    &:hover {
        opacity: .8;
        cursor: pointer;
    }
`

export const CancelPostButton = styled(CreatePostButton)`
    background-color: #eee;
    color: #666;
`

export const ActionsContainer = styled.div`
    display: flex;
    flex-direction: row;
    justify-content: space-between;
`

export const PostText = styled.textarea`
    border: none;
    resize: none;
    width: 90%;
    height: 80px;
    font-size: 16px;
    font-family: inherit;
    padding: 10px;
    border-radius: 6px;
    &:focus {
        border: 0;
        outline: 0;
        box-shadow: 0 0 9px 5px #ddd;
    }
`

export const ErrorMessage = styled.div`
    color: red;
    align-text: center;
    margin-top:10px;
`