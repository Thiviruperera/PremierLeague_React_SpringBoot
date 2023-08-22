import React, { Component } from "react";
import { Link } from "react-router-dom";

class HeaderComponent extends Component {
  constructor(props) {
    super(props);

    this.state = {};
  }

  render() {
    return (
      <div>
        <header>
          <nav className="navbar navbar-expand-md navbar-dark bg-dark">
            <div>
              <Link className="navbar-brand" to="/footballClubs">
                Home
              </Link>
            </div>
            <div>
              <Link className="navbar-brand" to="/matchesStatistics" style={{ marginLeft: 20 }}>
                Matches statistics
              </Link>
            </div>
            <div>
              <Link className="navbar-brand" to="/matchesStatisticsByDate" style={{ marginLeft: 20 }}>
                Matches statistics By Date
              </Link>
            </div>


          </nav>
        </header>
      </div>
    );
  }
}

export default HeaderComponent;
