import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';

function Registration() {
  const navigate = useNavigate();

  const [empid, setEmpid] = useState("");
  const [name, setName] = useState("");
  const [designation, setDesignation] = useState("");
  const [stack, setStack] = useState("");
  const [email, setEmail] = useState("");
  const [message, setMessage] = useState("");
  const [isSuccess, setIsSuccess] = useState(false);
  const [loading, setLoading] = useState(false);

  async function sendData(e) {
    e.preventDefault();
    setMessage("");
    setLoading(true);

    try {
      const response = await fetch("http://localhost:8080/v1/users/add", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          emp_id: Number(empid),
          emp_name: name,
          designation: designation,
          progStack: stack,
          email: email
        })
      });

      const text = await response.text();

      if (response.ok) {
        setIsSuccess(true);
        setMessage("Employee registered successfully! Redirecting to login...");
        setTimeout(() => {
          navigate("/login");
        }, 1500);
      } else {
        setIsSuccess(false);
        setMessage(text || "Failed to register employee.");
      }
    } catch (error) {
      console.error("Registration error:", error);
      setIsSuccess(false);
      setMessage("Error connecting to server. Is backend running on port 8080?");
    } finally {
      setLoading(false);
    }
  }

  return (
    <div className="container py-5 text-start">
      <div className="row justify-content-center">
        <div className="col-lg-8 col-xl-7">
          <div className="hrms-card p-4 p-sm-5">
            <div className="text-center mb-4">
              <div style={{
                width: "56px",
                height: "56px",
                borderRadius: "14px",
                background: "linear-gradient(135deg, #10b981 0%, #06b6d4 100%)",
                color: "white",
                display: "inline-flex",
                alignItems: "center",
                justifyContent: "center",
                fontSize: "1.6rem",
                marginBottom: "12px"
              }}>
                📝
              </div>
              <h3 className="fw-bold text-dark mb-1">Employee Registration</h3>
              <p className="text-muted small">Register new employee details to the HRMS database</p>
            </div>

            {message && (
              <div className={`alert ${isSuccess ? 'alert-success' : 'alert-danger'} py-2 small`} role="alert">
                {message}
              </div>
            )}

            <form onSubmit={sendData}>
              <div className="row g-3">
                <div className="col-sm-6">
                  <label htmlFor="empId" className="form-label small fw-semibold text-secondary">
                    Employee ID <span className="text-danger">*</span>
                  </label>
                  <input
                    type="number"
                    value={empid}
                    onChange={(e) => setEmpid(e.target.value)}
                    className="form-control"
                    id="empId"
                    placeholder="e.g. 105"
                    required
                  />
                </div>

                <div className="col-sm-6">
                  <label htmlFor="name" className="form-label small fw-semibold text-secondary">
                    Full Name <span className="text-danger">*</span>
                  </label>
                  <input
                    type="text"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                    className="form-control"
                    id="name"
                    placeholder="John Doe"
                    required
                  />
                </div>

                <div className="col-12">
                  <label htmlFor="email" className="form-label small fw-semibold text-secondary">
                    Email Address <span className="text-danger">*</span>
                  </label>
                  <input
                    type="email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    className="form-control"
                    id="email"
                    placeholder="john.doe@company.com"
                    required
                  />
                </div>

                <div className="col-sm-6">
                  <label htmlFor="designation" className="form-label small fw-semibold text-secondary">
                    Designation <span className="text-danger">*</span>
                  </label>
                  <input
                    type="text"
                    value={designation}
                    onChange={(e) => setDesignation(e.target.value)}
                    className="form-control"
                    id="designation"
                    placeholder="e.g. Software Engineer, QA"
                    required
                  />
                </div>

                <div className="col-sm-6">
                  <label htmlFor="stack" className="form-label small fw-semibold text-secondary">
                    Technology Stack <span className="text-danger">*</span>
                  </label>
                  <select
                    className="form-select"
                    value={stack}
                    onChange={(e) => setStack(e.target.value)}
                    id="stack"
                    required
                  >
                    <option value="">Select stack...</option>
                    <option value="Java Full Stack">Java Full Stack</option>
                    <option value="MERN Stack">MERN Stack</option>
                    <option value="Python Full Stack">Python Full Stack</option>
                    <option value=".NET Full Stack">.NET Full Stack</option>
                    <option value="DevOps / Cloud">DevOps / Cloud</option>
                  </select>
                </div>
              </div>

              <div className="mt-4 pt-2">
                <button
                  type="submit"
                  className="btn btn-primary w-100 py-2 fw-semibold shadow-sm"
                  disabled={loading}
                >
                  {loading ? "Registering Employee..." : "Complete Registration"}
                </button>
              </div>

              <div className="text-center mt-4 pt-3 border-top">
                <span className="text-muted small">Already registered? </span>
                <Link to="/login" className="fw-semibold text-primary text-decoration-none small">
                  Sign in here
                </Link>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Registration;
