import React, { Component } from 'react';

class Getuser extends Component {
  constructor(props) {
    super(props);
    this.state = {
      user: null,
      loading: true
    };
  }

  async componentDidMount() {
    try {
      const response = await fetch('https://api.randomuser.me/');
      const data = await response.json();
      this.setState({
        user: data.results[0],
        loading: false
      });
    } catch (error) {
      console.error('Error fetching user:', error);
    }
  }

  render() {
    const { user, loading } = this.state;

    if (loading) {
      return <h2 style={{ textAlign: 'center' }}>Loading user details...</h2>;
    }

    return (
      <div style={{ textAlign: 'center', marginTop: '50px' }}>
        <h1>
          {user.name.title} {user.name.first} {user.name.last}
        </h1>
        <img
          src={user.picture.large}
          alt="User"
          style={{
            borderRadius: '10px',
            width: '150px',
            height: '150px',
            marginTop: '20px'
          }}
        />
      </div>
    );
  }
}

export default Getuser;
