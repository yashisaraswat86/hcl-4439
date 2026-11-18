import { useFormStatus } from 'react-dom'

const SubmitButton = () => {
  const { pending } = useFormStatus()

  return (
    <button
      type="submit"
      disabled={pending}
      className="w-full rounded-xl bg-gradient-to-r from-blue-600 to-indigo-600 px-6 py-3 font-semibold text-white shadow-lg shadow-blue-500/20 transition-all duration-300 hover:-translate-y-0.5 hover:from-blue-700 hover:to-indigo-700 hover:shadow-xl focus:outline-none focus:ring-2 focus:ring-blue-400 focus:ring-offset-2 focus:ring-offset-slate-900 disabled:cursor-not-allowed disabled:opacity-50 disabled:hover:translate-y-0"
    >
      {pending ? (
        <span className="flex items-center justify-center gap-2">
          <span className="h-5 w-5 animate-spin rounded-full border-2 border-white/30 border-t-white" />
          Submitting...
        </span>
      ) : (
        'Submit'
      )}
    </button>
  )
}

export default SubmitButton