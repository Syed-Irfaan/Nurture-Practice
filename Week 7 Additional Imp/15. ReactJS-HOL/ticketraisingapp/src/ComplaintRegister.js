import React, { Component } from 'react';
import './ComplaintRegister.css';

class ComplaintRegister extends Component {
  constructor(props) {
    super(props);
    this.state = {
      ename: '',
      complaint: '',
      NumberHolder: ''
    };
  }

  handleChange = (event) => {
    this.setState({ [event.target.name]: event.target.value });
  };

  handleSubmit = (event) => {
    const refId = Math.floor(Math.random() * 100);
    this.setState({ NumberHolder: refId }, () => {
      const msg =
        'Thanks ' + this.state.ename +
        '\nYour Complaint was Submitted.\nTransaction ID is: ' + this.state.NumberHolder;
      alert(msg);
    });
    event.preventDefault();
  };

  render() {
    return (
      <div className="form-container">
        <h1 className="heading">Register your complaints here!!!</h1>
        <form onSubmit={this.handleSubmit}>
          <div className="form-row">
            <label className="form-label">Name:</label>
            <input
              type="text"
              name="ename"
              value={this.state.ename}
              onChange={this.handleChange}
              required
              className="form-input"
            />
          </div>

          <div className="form-row">
            <label className="form-label">Complaint:</label>
            <textarea
              name="complaint"
              rows="3"
              cols="30"
              value={this.state.complaint}
              onChange={this.handleChange}
              required
              className="form-input"
            />
          </div>

          <button type="submit" className="submit-btn">Submit</button>
        </form>
      </div>
    );
  }
}

export default ComplaintRegister;
