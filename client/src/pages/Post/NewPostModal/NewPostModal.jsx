import { useEffect, useState } from 'react'

import { OverlayContainer, Title, Line, CreatePostButton, CancelPostButton, PostContainer } from './styles'


export function NewPostModal({ onClose }) {

    return (
        <OverlayContainer>
            <PostContainer>
                <Title>Criar Post</Title>
                <Line />
                <div>
                    <div style={{ margin: '0 auto', marginTop: 10, maxWidth: 500, textAlign: 'center' }}>
                        From: <span></span>
                    </div>
                    <div style={{ margin: '0 auto', marginTop: 10, maxWidth: 500, textAlign: 'center' }}>
                        To: <span></span>
                    </div>
                    <div style={{ margin: '0 auto', marginTop: 10, maxWidth: 500, textAlign: 'center' }}>
                        Subject: <span></span>
                    </div>
                </div>
                <Line />
                <div style={{ display: 'flex', flexDirection: 'row', justifyContent: 'space-between' }}>
                    <CancelPostButton>Fechar</CancelPostButton>
                    <CreatePostButton>Criar</CreatePostButton>
                </div>
            </PostContainer>
        </OverlayContainer>
    )
}