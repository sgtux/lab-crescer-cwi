import './App.css'

import { useSelector } from 'react-redux'

import { Toolbar } from '../'
import { PostList, UserList } from '../../pages'
import { MenuStates } from '../../utils/constants'

export function App() {

  const { menu } = useSelector(state => state.appState)

  return (
    <div className="App">
      <Toolbar />
      {menu === MenuStates.POSTS ? <PostList /> : <UserList />}
    </div>
  )
}