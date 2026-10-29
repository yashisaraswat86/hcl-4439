import React from 'react';
import { Link } from 'react-router-dom';

function Home() {
  return (
    <div className="container py-5">
      {/* Hero Section */}
      <div className="row align-items-center py-5">
        <div className="col-lg-7 text-start">
          <span className="badge bg-primary-subtle text-primary border border-primary-subtle px-3 py-2 mb-3">
            HRMS 2.0 • Enterprise Attendance System
          </span>
          <h1 className="display-4 fw-bold mb-3" style={{ color: '#0f172a', letterSpacing: '-1px' }}>
            Smart Employee & <br />
            <span style={{
              background: 'linear-gradient(135deg, #4f46e5 0%, #06b6d4 100%)',
              WebkitBackgroundClip: 'text',
              WebkitTextFillColor: 'transparent'
            }}>
              Attendance Management
            </span>
          </h1>
          <p className="lead text-secondary mb-4" style={{ maxWidth: '540px' }}>
            Streamline your daily check-ins, record precise in/out times, and maintain employee directories seamlessly with our cloud-ready portal.
          </p>
          <div className="d-flex flex-wrap gap-3">
            <Link to="/dashboard" className="btn btn-primary btn-lg shadow-sm px-4">
              Go to Dashboard
            </Link>
            <Link to="/registration" className="btn btn-outline-secondary btn-lg px-4">
              Register New Employee
            </Link>
          </div>
        </div>

        <div className="col-lg-5 mt-4 mt-lg-0">
          <div className="hrms-card p-4 text-start">
            <div className="d-flex justify-content-between align-items-center mb-3">
              <span className="fw-bold text-dark fs-5">Quick Portal Access</span>
              <span className="badge bg-success">Live API</span>
            </div>
            <p className="text-muted small mb-4">
              Connect directly with Spring Boot REST backend running on port 8080.
            </p>
            <div className="d-grid gap-3">
              <Link to="/login" className="btn btn-light border text-start d-flex justify-content-between align-items-center p-3">
                <div>
                  <strong className="d-block text-dark">Employee Verification & Login</strong>
                  <small className="text-muted">Verify credentials via ID and Email</small>
                </div>
                <span className="text-primary fw-bold">→</span>
              </Link>
              <Link to="/dashboard" className="btn btn-light border text-start d-flex justify-content-between align-items-center p-3">
                <div>
                  <strong className="d-block text-dark">Daily Check In / Out</strong>
                  <small className="text-muted">Clock in/out & inspect logs</small>
                </div>
                <span className="text-primary fw-bold">→</span>
              </Link>
            </div>
          </div>
        </div>
      </div>

      {/* Feature Cards Section */}
      <div className="row g-4 mt-4 text-start">
        <div className="col-md-4">
          <div className="hrms-card p-4 h-100">
            <div style={{
              width: "48px",
              height: "48px",
              borderRadius: "10px",
              background: "#e0e7ff",
              color: "#4f46e5",
              display: "flex",
              alignItems: "center",
              justifyContent: "center",
              fontSize: "1.5rem",
              marginBottom: "16px"
            }}>
              ⏱️
            </div>
            <h5 className="fw-bold text-dark">Accurate Time Tracking</h5>
            <p className="text-secondary small mb-0">
              Captures precise timestamps for check-in and check-out to calculate work duration with duplicate prevention.
            </p>
          </div>
        </div>

        <div className="col-md-4">
          <div className="hrms-card p-4 h-100">
            <div style={{
              width: "48px",
              height: "48px",
              borderRadius: "10px",
              background: "#dcfce7",
              color: "#16a34a",
              display: "flex",
              alignItems: "center",
              justifyContent: "center",
              fontSize: "1.5rem",
              marginBottom: "16px"
            }}>
              👥
            </div>
            <h5 className="fw-bold text-dark">Employee Directory</h5>
            <p className="text-secondary small mb-0">
              Centralized record management linking designations, technology stacks, and unique IDs directly to MySQL.
            </p>
          </div>
        </div>

        <div className="col-md-4">
          <div className="hrms-card p-4 h-100">
            <div style={{
              width: "48px",
              height: "48px",
              borderRadius: "10px",
              background: "#fef3c7",
              color: "#d97706",
              display: "flex",
              alignItems: "center",
              justifyContent: "center",
              fontSize: "1.5rem",
              marginBottom: "16px"
            }}>
              ⚡
            </div>
            <h5 className="fw-bold text-dark">RESTful Spring Boot</h5>
            <p className="text-secondary small mb-0">
              Backed by Spring Data JPA with instant status feedback (201 Created, 409 Conflict, 404 Not Found).
            </p>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Home;
