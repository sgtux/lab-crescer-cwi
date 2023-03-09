import './App.css'

import { useSelector } from 'react-redux'

import { Toolbar, Footer } from '../'
import { PostList, UserList, EditProfile, Hash } from '../../pages'
import { MenuStates } from '../../utils/constants'

export function App() {

  const { menu } = useSelector(state => state.appState)

  function resolveScreen() {
    switch (menu) {
      case MenuStates.POSTS:
        return <PostList />
      case MenuStates.EDIT_PROFILE:
        return <EditProfile />
      case MenuStates.USUARIOS:
        return <UserList />
      case MenuStates.HASH:
        return <Hash />
    }
  }

  return (
    <div className="App">
      <Toolbar />
      {resolveScreen()}
      <Footer />
    </div>
  )
}