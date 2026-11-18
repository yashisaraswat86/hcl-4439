import { useState, useActionState, useRef } from 'react'
import api from './Api'
import SubmitButton from './SubmitButton'

const AuthForm = ({ onLogin }) => {
  const [mode, setMode] = useState('login')
  const formRef = useRef(null)

  const submitAction = async (prevState, formData) => {
    const username = formData.get('username')?.trim()
    const password = formData.get('password')?.trim()

    if (!username || !password) {
      return {
        error: 'Username and password are required',
        msg: 'Please fill in both fields',
        username: username || '',
      }
    }

    try {
      if (mode === 'register') {
        await api.post('/auth/register', { username, password })
        setMode('login')
        formRef.current?.reset()

        return {
          error: null,
          msg: 'Registration successful. Please log in.',
          username,
        }
      }

      // login mode
      const { data } = await api.post('/auth/login', { username, password })
      if (data.token) {
        localStorage.setItem('token', data.token)
      }
      localStorage.setItem('username', data.username || username)
      onLogin?.(data)
      formRef.current?.reset()

      return {
        error: null,
        msg: 'Login successful',
        username: data.username || username,
      }
    } catch (error) {
      return {
        error:
          error.response?.data?.error ||
          error.response?.data?.message ||
          'An error occurred',
        msg: '',
        username,
      }
    }
  }

  const [state, formAction, isPending] = useActionState(submitAction, {
    error: null,
    msg: '',
    username: '',
  })

  return (
    <form
      ref={formRef}
      action={formAction}
      className="w-full max-w-md space-y-5 rounded-2xl border border-white/10 bg-slate-900/80 p-6 shadow-2xl shadow-black/20 backdrop-blur-xl sm:p-8"
    >
      <div className="mb-6 text-center">
        <h3 className="text-3xl font-bold tracking-tight text-white">
          {mode === 'login' ? 'Welcome Back' : 'Create Account'}
        </h3>
        <p className="mt-2 text-sm text-slate-400">
          {mode === 'login'
            ? 'Log in to continue to your account'
            : 'Register to get started'}
        </p>
      </div>

      <div className="space-y-2">
        <label
          htmlFor="username"
          className="block text-sm font-medium text-slate-200"
        >
          Username
        </label>
        <input
          type="text"
          name="username"
          id="username"
          placeholder="Enter your username"
          defaultValue={state.username}
          required
          disabled={isPending}
          className="w-full rounded-xl border border-slate-700 bg-slate-800/80 px-4 py-3 text-white placeholder-slate-500 outline-none transition-all duration-200 focus:border-blue-500 focus:ring-2 focus:ring-blue-500/20 disabled:cursor-not-allowed disabled:opacity-50"
        />
      </div>

      <div className="space-y-2">
        <label
          htmlFor="password"
          className="block text-sm font-medium text-slate-200"
        >
          Password
        </label>
        <input
          type="password"
          name="password"
          id="password"
          placeholder="Enter your password"
          required
          disabled={isPending}
          className="w-full rounded-xl border border-slate-700 bg-slate-800/80 px-4 py-3 text-white placeholder-slate-500 outline-none transition-all duration-200 focus:border-blue-500 focus:ring-2 focus:ring-blue-500/20 disabled:cursor-not-allowed disabled:opacity-50"
        />
      </div>

      <SubmitButton />

      <button
        type="button"
        onClick={() => setMode(mode === 'login' ? 'register' : 'login')}
        disabled={isPending}
        className="w-full rounded-xl border border-slate-700 bg-slate-800/60 px-4 py-3 font-medium text-slate-300 transition-all duration-200 hover:border-blue-500/50 hover:bg-slate-800 hover:text-white disabled:cursor-not-allowed disabled:opacity-50"
      >
        {mode === 'login'
          ? "Don't have an account? Register"
          : 'Already have an account? Login'}
      </button>

      {state.msg && (
        <p
          role="status"
          className="rounded-lg border border-emerald-500/20 bg-emerald-500/10 px-4 py-3 text-sm font-medium text-emerald-400"
        >
          {state.msg}
        </p>
      )}

      {state.error && (
        <p
          role="alert"
          className="rounded-lg border border-red-500/20 bg-red-500/10 px-4 py-3 text-sm font-medium text-red-400"
        >
          {state.error}
        </p>
      )}
    </form>
  )
}

export default AuthForm