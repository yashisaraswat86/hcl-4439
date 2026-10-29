import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

function Dashboard() {
  const navigate = useNavigate();

  const [user, setUser] = useState(null);
  const [empId, setEmpId] = useState("");
  const [comment, setComment] = useState("");
  const [markStatus, setMarkStatus] = useState("");
  const [statusType, setStatusType] = useState("");
  const [loadingAction, setLoadingAction] = useState(""); // 'in' or 'out'
  const [attendanceList, setAttendanceList] = useState([]);
  const [loadingList, setLoadingList] = useState(false);
  const [searchTerm, setSearchTerm] = useState("");
  const [currentTime, setCurrentTime] = useState(new Date());

  // Real-time clock update
  useEffect(() => {
    const timer = setInterval(() => setCurrentTime(new Date()), 1000);
    return () => clearInterval(timer);
  }, []);

  useEffect(() => {
    const savedUser = localStorage.getItem("user");
    if (savedUser) {
      try {
        const parsed = JSON.parse(savedUser);
        setUser(parsed);
        if (parsed.emp_id) {
          setEmpId(parsed.emp_id);
        }
      } catch (err) {
        console.error("Failed to parse user session", err);
      }
    }
    fetchAttendance();
  }, []);

  async function fetchAttendance() {
    setLoadingList(true);
    try {
      const res = await fetch("http://localhost:8080/attendance");
      if (res.ok) {
        const data = await res.json();
        setAttendanceList(Array.isArray(data) ? data : []);
      }
    } catch (err) {
      console.error("Error fetching attendance list:", err);
    } finally {
      setLoadingList(false);
    }
  }

  // Handle Check-In (In Time)
  async function handleCheckIn(e) {
    if (e) e.preventDefault();
    setMarkStatus("");

    const targetEmpId = empId || user?.emp_id;
    if (!targetEmpId) {
      setStatusType("danger");
      setMarkStatus("Please enter an Employee ID.");
      return;
    }

    setLoadingAction("in");

    try {
      const response = await fetch("http://localhost:8080/markattendance", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          employee: {
            emp_id: Number(targetEmpId)
          },
          comment: comment.trim() || "Checked in"
        })
      });

      const text = await response.text();

      if (response.status === 201 || text.toLowerCase().includes("successfully")) {
        setStatusType("success");
        setMarkStatus("Check-In successful! In Time recorded.");
        setComment("");
        fetchAttendance();
      } else if (response.status === 409 || text.toLowerCase().includes("already")) {
        setStatusType("warning");
        setMarkStatus("Already checked in for today.");
      } else if (response.status === 404) {
        setStatusType("danger");
        setMarkStatus(`Employee not found with ID: ${targetEmpId}`);
      } else {
        setStatusType("danger");
        setMarkStatus(text || "Failed to check in.");
      }
    } catch (error) {
      console.error("Error during check-in:", error);
      setStatusType("danger");
      setMarkStatus("Failed to connect to backend server.");
    } finally {
      setLoadingAction("");
    }
  }

  // Handle Check-Out (Out Time)
  async function handleCheckOut(e) {
    if (e) e.preventDefault();
    setMarkStatus("");

    const targetEmpId = empId || user?.emp_id;
    if (!targetEmpId) {
      setStatusType("danger");
      setMarkStatus("Please enter an Employee ID.");
      return;
    }

    setLoadingAction("out");

    try {
      const response = await fetch("http://localhost:8080/checkout", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          employee: {
            emp_id: Number(targetEmpId)
          },
          comment: comment.trim() || "Checked out"
        })
      });

      const text = await response.text();

      if (response.ok) {
        setStatusType("success");
        setMarkStatus("Check-Out successful! Out Time recorded.");
        setComment("");
        fetchAttendance();
      } else if (response.status === 409 || text.toLowerCase().includes("already")) {
        setStatusType("warning");
        setMarkStatus(text || "Check-Out already recorded for today.");
      } else if (response.status === 404) {
        setStatusType("danger");
        setMarkStatus(text || "No check-in record found for today. Please check in first.");
      } else {
        setStatusType("danger");
        setMarkStatus(text || "Failed to check out.");
      }
    } catch (error) {
      console.error("Error during check-out:", error);
      setStatusType("danger");
      setMarkStatus("Failed to connect to backend server.");
    } finally {
      setLoadingAction("");
    }
  }

  function formatTime(isoString) {
    if (!isoString) return null;
    try {
      return new Date(isoString).toLocaleTimeString([], {
        hour: '2-digit',
        minute: '2-digit',
        second: '2-digit'
      });
    } catch {
      return isoString;
    }
  }

  function calculateDuration(inTime, outTime) {
    if (!inTime || !outTime) return null;
    try {
      const diffMs = new Date(outTime) - new Date(inTime);
      if (diffMs <= 0) return "0m";
      const totalMins = Math.floor(diffMs / (1000 * 60));
      const hours = Math.floor(totalMins / 60);
      const mins = totalMins % 60;
      return hours > 0 ? `${hours}h ${mins}m` : `${mins}m`;
    } catch {
      return null;
    }
  }

  // Filter records by search term (name or id)
  const filteredList = attendanceList.filter((item) => {
    const term = searchTerm.toLowerCase();
    const empIdMatch = item.employee?.emp_id?.toString().includes(term);
    const nameMatch = item.employee?.emp_name?.toLowerCase().includes(term);
    return empIdMatch || nameMatch;
  });

  return (
    <div className="container py-4 text-start">
      {/* Top Welcome & Live Clock Bar */}
      <div className="row g-3 mb-4">
        <div className="col-md-6 col-lg-8">
          <div className="stat-card h-100">
            <div className="stat-icon" style={{ background: '#e0e7ff', color: '#4f46e5' }}>
              👤
            </div>
            <div>
              <h5 className="fw-bold mb-0 text-dark">
                {user ? `Hello, ${user.email ? user.email.split('@')[0] : `Employee #${user.emp_id}`}` : "HRMS Attendance Control"}
              </h5>
              <small className="text-muted">
                {user?.emp_id ? `Active Session • Employee ID: ${user.emp_id}` : "Ready to mark attendance"}
              </small>
            </div>
          </div>
        </div>

        <div className="col-md-6 col-lg-4">
          <div className="stat-card h-100 justify-content-between">
            <div className="d-flex align-items-center gap-3">
              <div className="stat-icon" style={{ background: '#ecfdf5', color: '#10b981' }}>
                🕒
              </div>
              <div>
                <small className="text-muted text-uppercase fw-semibold" style={{ fontSize: '0.7rem' }}>
                  {currentTime.toLocaleDateString(undefined, { weekday: 'short', month: 'short', day: 'numeric', year: 'numeric' })}
                </small>
                <h5 className="fw-bold mb-0 text-dark">
                  {currentTime.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit', second: '2-digit' })}
                </h5>
              </div>
            </div>
            <span className="badge bg-success-subtle text-success border border-success-subtle">
              Live
            </span>
          </div>
        </div>
      </div>

      <div className="row g-4">
        {/* Check-In / Check-Out Action Panel */}
        <div className="col-lg-5">
          <div className="hrms-card p-4 h-100">
            <div className="d-flex justify-content-between align-items-center mb-3">
              <h5 className="fw-bold text-dark mb-0">Record Attendance</h5>
              <span className="badge bg-primary-subtle text-primary">In / Out System</span>
            </div>
            <p className="text-muted small mb-4">
              Submit your ID to punch In at start of day, and punch Out when departing.
            </p>

            {markStatus && (
              <div className={`alert alert-${statusType} py-2 small`} role="alert">
                {markStatus}
              </div>
            )}

            <div>
              <div className="mb-3">
                <label htmlFor="dashEmpId" className="form-label small fw-semibold text-secondary">
                  Employee ID <span className="text-danger">*</span>
                </label>
                <input
                  type="number"
                  id="dashEmpId"
                  value={empId}
                  onChange={(e) => setEmpId(e.target.value)}
                  className="form-control"
                  placeholder="Enter your employee ID (e.g. 101)"
                  required
                />
              </div>

              <div className="mb-3">
                <label htmlFor="dashComment" className="form-label small fw-semibold text-secondary">
                  Work Mode / Note
                </label>
                <input
                  type="text"
                  id="dashComment"
                  value={comment}
                  onChange={(e) => setComment(e.target.value)}
                  className="form-control"
                  placeholder="e.g. In Office, Remote, Field work"
                />
                <div className="d-flex gap-2 mt-2">
                  <button
                    type="button"
                    onClick={() => setComment("In Office")}
                    className="btn btn-sm btn-light border text-muted py-0 px-2"
                    style={{ fontSize: '0.75rem' }}
                  >
                    🏢 Office
                  </button>
                  <button
                    type="button"
                    onClick={() => setComment("Work from Home")}
                    className="btn btn-sm btn-light border text-muted py-0 px-2"
                    style={{ fontSize: '0.75rem' }}
                  >
                    🏠 WFH
                  </button>
                  <button
                    type="button"
                    onClick={() => setComment("On Site")}
                    className="btn btn-sm btn-light border text-muted py-0 px-2"
                    style={{ fontSize: '0.75rem' }}
                  >
                    📍 Site
                  </button>
                </div>
              </div>

              {/* Action Buttons */}
              <div className="row g-2 pt-2">
                <div className="col-sm-6">
                  <button
                    type="button"
                    className="btn btn-success w-100 py-2 d-flex align-items-center justify-content-center gap-2 shadow-sm"
                    onClick={handleCheckIn}
                    disabled={loadingAction !== ""}
                  >
                    <span>🟢</span>
                    <span>{loadingAction === "in" ? "Recording..." : "Check In"}</span>
                  </button>
                </div>
                <div className="col-sm-6">
                  <button
                    type="button"
                    className="btn btn-warning w-100 py-2 d-flex align-items-center justify-content-center gap-2 shadow-sm"
                    onClick={handleCheckOut}
                    disabled={loadingAction !== ""}
                  >
                    <span>🔴</span>
                    <span>{loadingAction === "out" ? "Recording..." : "Check Out"}</span>
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>

        {/* Live Attendance Table */}
        <div className="col-lg-7">
          <div className="hrms-card h-100 d-flex flex-column">
            <div className="hrms-card-header d-flex flex-wrap justify-content-between align-items-center gap-2">
              <div>
                <h5 className="fw-bold text-dark mb-0">Attendance Log</h5>
                <small className="text-muted">{attendanceList.length} total entries recorded</small>
              </div>
              <div className="d-flex align-items-center gap-2">
                <input
                  type="text"
                  value={searchTerm}
                  onChange={(e) => setSearchTerm(e.target.value)}
                  className="form-control form-control-sm"
                  style={{ width: "170px" }}
                  placeholder="🔍 Search name/ID..."
                />
                <button
                  className="btn btn-outline-secondary btn-sm"
                  onClick={fetchAttendance}
                  disabled={loadingList}
                  title="Refresh list"
                >
                  {loadingList ? "..." : "🔄"}
                </button>
              </div>
            </div>

            <div className="card-body p-0 flex-grow-1">
              <div className="table-responsive" style={{ maxHeight: "380px" }}>
                <table className="table table-hover align-middle mb-0">
                  <thead className="table-light sticky-top">
                    <tr style={{ fontSize: '0.85rem' }}>
                      <th className="ps-3">Employee</th>
                      <th>Date</th>
                      <th>In Time</th>
                      <th>Out Time</th>
                      <th>Duration</th>
                      <th className="pe-3">Status</th>
                    </tr>
                  </thead>
                  <tbody>
                    {filteredList.length === 0 ? (
                      <tr>
                        <td colSpan="6" className="text-center py-5 text-muted">
                          {loadingList ? "Loading records from database..." : "No matching attendance records found."}
                        </td>
                      </tr>
                    ) : (
                      filteredList.map((item, index) => {
                        const inFormatted = formatTime(item.inTime);
                        const outFormatted = formatTime(item.outTime);
                        const duration = calculateDuration(item.inTime, item.outTime);

                        return (
                          <tr key={item.attendanceId || index} style={{ fontSize: '0.9rem' }}>
                            <td className="ps-3">
                              <div className="d-flex align-items-center gap-2">
                                <span className="badge bg-secondary-subtle text-secondary border">
                                  #{item.employee?.emp_id}
                                </span>
                                <div>
                                  <strong className="d-block text-dark" style={{ lineHeight: '1.2' }}>
                                    {item.employee?.emp_name || "Employee"}
                                  </strong>
                                  <small className="text-muted" style={{ fontSize: '0.75rem' }}>
                                    {item.comment || "-"}
                                  </small>
                                </div>
                              </div>
                            </td>
                            <td>{item.attendancedate}</td>
                            <td>
                              {inFormatted ? (
                                <span className="badge bg-success-subtle text-success border border-success-subtle">
                                  {inFormatted}
                                </span>
                              ) : (
                                <span className="text-muted">-</span>
                              )}
                            </td>
                            <td>
                              {outFormatted ? (
                                <span className="badge bg-danger-subtle text-danger border border-danger-subtle">
                                  {outFormatted}
                                </span>
                              ) : (
                                <span className="badge bg-warning-subtle text-warning border border-warning-subtle">
                                  Active
                                </span>
                              )}
                            </td>
                            <td>
                              {duration ? (
                                <span className="fw-semibold text-secondary">{duration}</span>
                              ) : (
                                <span className="text-muted">-</span>
                              )}
                            </td>
                            <td className="pe-3">
                              {outFormatted ? (
                                <span className="badge bg-success">Completed</span>
                              ) : inFormatted ? (
                                <span className="badge bg-primary">Checked In</span>
                              ) : (
                                <span className="badge bg-secondary">Pending</span>
                              )}
                            </td>
                          </tr>
                        );
                      })
                    )}
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Dashboard;
