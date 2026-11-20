import { useState } from 'react'
import Student from './components/Student'
import StudentForm from './components/StudentForm'


function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <StudentForm />
    </>
  )
}

export default App
