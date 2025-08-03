import React from "react";

class CountPeople extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      entrycount: 0,
      exitcount: 0
    };
  }

  updateEntry = () => {
    this.setState(prevState => ({
      entrycount: prevState.entrycount + 1
    }));
  };

  updateExit = () => {
    this.setState(prevState => ({
      exitcount: prevState.exitcount + 1
    }));
  };

  render() {
    return (
      <div style={{ 
        display: "flex", 
        justifyContent: "center", 
        alignItems: "center", 
        height: "100vh", 
        fontFamily: "Arial" 
      }}>
        <div style={{ display: "flex", gap: "100px" }}>
          {/* Entry Section */}
          <div>
            <button 
              onClick={this.updateEntry} 
              style={{
                backgroundColor: "lightgreen", 
                color: "Black",  
                padding: "5px 10px", 
                border: "box", 
                borderRadius: "4px"
              }}>
              Login
            </button>
            <span style={{ marginLeft: "10px", fontSize: "18px" }}>
              {this.state.entrycount} People Entered!!!
            </span>
          </div>

          {/* Exit Section */}
          <div>
            <button 
              onClick={this.updateExit} 
              style={{
                backgroundColor: "lightgreen", 
                color: "black",  
                padding: "5px 10px", 
                border: "box", 
                borderRadius: "4px"
              }}>
              Exit
            </button>
            <span style={{ marginLeft: "10px", fontSize: "18px" }}>
              {this.state.exitcount} People Left!!!
            </span>
          </div>
        </div>
      </div>
    );
  }
}

export default CountPeople;
