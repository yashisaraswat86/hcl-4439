import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';

function Login() {
  const navigate = useNavigate();

  const [empid, setEmpid] = useState("");
  const [email, setEmail] = useState("");
  const [message, setMessage] = useState("");
  const [loading, setLoading] = useState(false);
  const [isSuccess, setIsSuccess] = useState(false);

  async function handleLogin(e) {
    e.preventDefault();
    setMessage("");
    setLoading(true);

    try {
      const payload = {};
      if (empid) payload.emp_id = Number(empid);
      if (email) payload.email = email;

      const response = await fetch("http://localhost:8080/v1/users/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload)
      });

      const text = await response.text();

      if (response.ok && text.toLowerCase().includes("successful")) {
        setIsSuccess(true);
        setMessage("Verification successful! Redirecting to dashboard...");

        localStorage.setItem("user", JSON.stringify({
          emp_id: empid ? Number(empid) : null,
          email: email
        }));

        setTimeout(() => {
          navigate("/dashboard");
        }, 1000);
      } else {
        setIsSuccess(false);
        setMessage(text || "Login verification failed. Please check your credentials.");
      }
    } catch (error) {
      console.error("Login error:", error);
      setIsSuccess(false);
      setMessage("Cannot reach backend server. Please verify Spring Boot is running on port 8080.");
    } finally {
      setLoading(false);
    }
  }

  return (
    <div className="container py-5 text-start">
      <div className="row justify-content-center">
        <div className="col-md-6 col-lg-5">
          <div className="hrms-card p-4 p-sm-5">
            <div className="text-center mb-4">
              <div style={{
                width: "56px",
                height: "56px",
                borderRadius: "14px",
                background: "linear-gradient(135deg, #4f46e5 0%, #06b6d4 100%)",
                color: "white",
                display: "inline-flex",
                alignItems: "center",
                justifyContent: "center",
                fontSize: "1.6rem",
                marginBottom: "12px"
              }}>
                🔑
              </div>
              <h3 className="fw-bold text-dark mb-1">Welcome Back</h3>
              <p className="text-muted small">Enter your Employee ID or Registered Email to verify</p>
            </div>

            {message && (
              <div className={`alert ${isSuccess ? 'alert-success' : 'alert-danger'} py-2 small`} role="alert">
                {message}
              </div>
            )}

            <form onSubmit={handleLogin}>
              <div className="mb-3">
                <label htmlFor="loginEmpId" className="form-label small fw-semibold text-secondary">
                  Employee ID
                </label>
                <input
                  type="number"
                  value={empid}
                  onChange={(e) => setEmpid(e.target.value)}
                  className="form-control"
                  id="loginEmpId"
                  placeholder="e.g. 101"
                />
                <div className="form-text text-muted small">
                  Optional if email is provided
                </div>
              </div>

              <div className="mb-4">
                <label htmlFor="loginEmail" className="form-label small fw-semibold text-secondary">
                  Email Address <span className="text-danger">*</span>
                </label>
                <input
                  type="email"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  className="form-control"
                  id="loginEmail"
                  placeholder="name@company.com"
                  required
                />
              </div>

              <button
                type="submit"
                className="btn btn-primary w-100 py-2 fw-semibold shadow-sm"
                disabled={loading}
              >
                {loading ? "Verifying Credentials..." : "Sign In & Access Dashboard"}
              </button>

              <div className="text-center mt-4 pt-3 border-top">
                <span className="text-muted small">New employee? </span>
                <Link to="/registration" className="fw-semibold text-primary text-decoration-none small">
                  Create an account
                </Link>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Login;
