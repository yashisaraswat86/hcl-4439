import { useState } from 'react'
import AuthForm from './AuthForm'
import PostForm from './PostForm'

const App = () => {
  const [currentUser, setCurrentUser] = useState(() => localStorage.getItem('username') || '')

  const handleLogin = (data) => {
    setCurrentUser(data?.username || localStorage.getItem('username') || '')
  }

  const handleLogout = () => {
    localStorage.removeItem('token')
    localStorage.removeItem('username')
    setCurrentUser('')
  }

  return (
    <div className="min-h-screen bg-gradient-to-br from-slate-950 via-slate-900 to-indigo-950 px-4 py-10">
      <div className="mx-auto flex max-w-4xl flex-col items-center gap-8">
        {currentUser && (
          <div className="flex w-full max-w-md items-center justify-between rounded-xl border border-white/10 bg-slate-900/80 px-5 py-3 text-white backdrop-blur-xl">
            <span className="text-sm text-slate-300">
              Logged in as <strong className="text-blue-400">{currentUser}</strong>
            </span>
            <button
              onClick={handleLogout}
              className="rounded-lg bg-red-500/20 px-3 py-1.5 text-xs font-semibold text-red-300 transition-colors hover:bg-red-500/30"
            >
              Logout
            </button>
          </div>
        )}
        <AuthForm onLogin={handleLogin} />
        <PostForm />
      </div>
    </div>
  )
}

export default App
