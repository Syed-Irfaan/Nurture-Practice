import React, { Component } from 'react';
import './Register.css';

class Register extends Component {
  constructor(props) {
    super(props);
    this.state = {
      fullName: '',
      email: '',
      password: '',
      errors: {
        fullName: '',
        email: '',
        password: ''
      }
    };
  }

  handleChange = (event) => {
    const { name, value } = event.target;
    let errors = this.state.errors;

    switch (name) {
      case 'fullName':
        errors.fullName = value.length < 5
          ? 'Full Name must be 5 characters long!'
          : '';
        break;

      case 'email':
        const validEmailRegex = RegExp(
          /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@(([^<>()[\]\\.,;:\s@"]+\.)+[^<>()[\]\\.,;:\s@"]{2,})$/i
        );
        errors.email = validEmailRegex.test(value)
          ? ''
          : 'Email is not valid!';
        break;

      case 'password':
        errors.password = value.length < 8
          ? 'Password must be 8 characters long!'
          : '';
        break;

      default:
        break;
    }

    this.setState({ [name]: value, errors });
  };

  validateForm = (errors) => {
    return Object.values(errors).every(val => val === '');
  };

  handleSubmit = (event) => {
    event.preventDefault();

    const { fullName, email, password, errors } = this.state;

    if (this.validateForm(errors)) {
      alert('Form submitted successfully!');
    } else {
      if (errors.fullName) {
        alert(errors.fullName);
      }
      if (errors.email) {
        alert(errors.email);
      }
      if (errors.password) {
        alert(errors.password);
      }
    }
  };

  render() {
    return (
      <div className="form-container">
        <h1 className="heading">Register Here!!!</h1>
        <form onSubmit={this.handleSubmit} noValidate>
          <div className="form-row">
            <label>Name:</label>
            <input
              type="text"
              name="fullName"
              value={this.state.fullName}
              onChange={this.handleChange}
              required
            />
          </div>

          <div className="form-row">
            <label>Email:</label>
            <input
              type="email"
              name="email"
              value={this.state.email}
              onChange={this.handleChange}
              required
            />
          </div>

          <div className="form-row">
            <label>Password:</label>
            <input
              type="password"
              name="password"
              value={this.state.password}
              onChange={this.handleChange}
              required
            />
          </div>

          <button type="submit" className="submit-btn">Submit</button>
        </form>
      </div>
    );
  }
}

export default Register;
