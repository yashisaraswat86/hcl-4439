import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Navbar from './component/Navbar';
import Home from './component/Home';
import Login from './component/Login';
import Dashboard from './component/Dashboard';
import Registration from './component/Registration';

function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <main className="flex-grow-1">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/registration" element={<Registration />} />
        </Routes>
      </main>
      <footer className="py-3 text-center text-muted border-top bg-white mt-auto">
        <small>© 2026 ABES HCL HRMS Portal. All rights reserved.</small>
      </footer>
    </BrowserRouter>
  );
}

export default App;
