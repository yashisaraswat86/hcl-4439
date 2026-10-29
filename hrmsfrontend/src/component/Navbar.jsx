import React from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';

function Navbar() {
  const location = useLocation();
  const navigate = useNavigate();

  const savedUser = localStorage.getItem("user");
  let user = null;
  if (savedUser) {
    try {
      user = JSON.parse(savedUser);
    } catch {
      user = null;
    }
  }

  function handleLogout() {
    localStorage.removeItem("user");
    navigate("/login");
  }

  const isActive = (path) => location.pathname === path ? "hrms-nav-link active" : "hrms-nav-link";

  return (
    <nav className="navbar navbar-expand-lg hrms-navbar sticky-top py-2">
      <div className="container">
        <Link className="navbar-brand d-flex align-items-center gap-2 fw-bold text-primary fs-4" to="/">
          <span style={{
            background: "linear-gradient(135deg, #4f46e5 0%, #06b6d4 100%)",
            color: "white",
            padding: "6px 12px",
            borderRadius: "8px",
            fontSize: "1.1rem"
          }}>
            HCL
          </span>
          <span className="text-dark">HRMS Portal</span>
        </Link>

        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#hrmsNavbarNav"
          aria-controls="hrmsNavbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>

        <div className="collapse navbar-collapse" id="hrmsNavbarNav">
          <ul className="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-3 gap-1">
            <li className="nav-item">
              <Link className={isActive("/")} to="/">Home</Link>
            </li>
            <li className="nav-item">
              <Link className={isActive("/dashboard")} to="/dashboard">Dashboard</Link>
            </li>
            <li className="nav-item">
              <Link className={isActive("/registration")} to="/registration">Register Employee</Link>
            </li>
          </ul>

          <div className="d-flex align-items-center gap-3">
            {user ? (
              <>
                <div className="d-flex align-items-center gap-2">
                  <div style={{
                    width: "36px",
                    height: "36px",
                    borderRadius: "50%",
                    background: "#e0e7ff",
                    color: "#4338ca",
                    display: "flex",
                    alignItems: "center",
                    justifyContent: "center",
                    fontWeight: "bold"
                  }}>
                    {user.email ? user.email.charAt(0).toUpperCase() : (user.emp_id || "U")}
                  </div>
                  <div className="d-none d-md-block text-start" style={{ lineHeight: "1.2" }}>
                    <small className="fw-bold d-block text-dark">
                      {user.email ? user.email.split('@')[0] : `Emp #${user.emp_id}`}
                    </small>
                    <small className="text-muted" style={{ fontSize: "0.75rem" }}>
                      {user.emp_id ? `ID: ${user.emp_id}` : "Online"}
                    </small>
                  </div>
                </div>
                <button
                  onClick={handleLogout}
                  className="btn btn-outline-danger btn-sm px-3"
                >
                  Logout
                </button>
              </>
            ) : (
              <Link to="/login" className="btn btn-primary btn-sm px-3">
                Sign In
              </Link>
            )}
          </div>
        </div>
      </div>
    </nav>
  );
}

export default Navbar;

