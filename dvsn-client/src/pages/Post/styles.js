import styled from 'styled-components'

export const PostListContainer = styled.div`
    margin-top: 20px;
`

export const PostCard = styled.div`
    padding: 10px;
    margin: 0 auto;
    margin-top: 20px;
    box-shadow: 1px 1px 2px #bbb;
    text-align: center;
    max-width: 600px;
    overflow: hidden;
    border-radius: 10px;
    background-color: #fff;
    & > img {
        width: 200px;
        height: 180px;
    }
`

export const PostUserBox = styled.div`  
    text-align: left;
    display: flex;
    & > div {
        padding-left: 10px;
        & > h4 {
            margin: 0;
            padding: 0;
        }
    }
`

export const PostProfileImage = styled.img`
    height: 50px;
    width: 50px;
    border-radius: 50%;
`
  
export const PostCardHeader = styled.div`
    display: flex;
    justify-content: space-between;
    padding: 10px;
`
  
export const PostCardBody = styled.div`
    margin: 10px;
    & h6 {
        text-align: left;
    }

    & img {
        width: 500px;
        border-radius: 20px;
    }
`

export const PostFooter = styled.div`
    margin-top: 10px;
    text-align: left;
    padding: 10px;
`

export const CommentBox = styled.div`
    text-align: left;
    display: flex;
    margin-top: 10px;
    &  > div {
        margin-left: 10px;
        background-color: #ddd;
        padding: 5px 10px;
        border-radius: 15px;
    }
`
  
export const NewCommentBox = styled.div`
    margin-top: 10px;
    padding: 10px;
    text-align: center;
    background-color: #ddd;
    border-radius: 20px;
    & input {
        width: 85%;
        background-color: #ddd;
        border: none;
        &:focus {
            border: none;
            outline: none;
            box-shadow: 2px 2px 2px 1px #ccc;
        }
    }
`
 
export const BtnSendComment = styled.button`
    background-color: #ddd;
    border: none;
    color: #555;
    font-weight: bold;
    &:hover {
        opacity: .8;
        cursor: pointer;
    }
`

export const NewPostButtonBox = styled.div`
    position: fixed;
    right: 20px;
    bottom: 20px;
    z-index: 99;
`

export const BtnRemove = styled.button`
    color: #dc3545;
    border: none;
    background-color: transparent;
    font-size: 20px;
    transition: 200ms;
    &:hover {
        cursor: pointer;
        opacity: .8;
    }
`
    
export const BtnRemoveComment = styled(BtnRemove)`
    font-size: 14px;
    margin-left:10px;
`

export const Line = styled.div`
    margin: 10px 0;
    border: 1px solid #dbdada;
`

export const PostText = styled.div`
    text-align: left;
    padding-bottom: 10px;
`

export const NewPostButton = styled.button`
    background-color: #1877f2;
    border: none;
    border-radius: 6px;
    color: white;
    height: 50px;
    font-size: 20px;
    transition: 200ms;
    padding: 10px;
    &:hover {
        opacity: .8;
        cursor: pointer;
    }
`