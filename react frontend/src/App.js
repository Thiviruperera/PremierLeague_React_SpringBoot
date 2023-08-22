import React from "react";
import "./App.css";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import ListFootballClubComponent from "./components/ListFootballClubComponent";
import HeaderComponent from "./components/HeaderComponent";
import FooterComponent from "./components/FooterComponent";
import CreateFootballClubComponent from "./components/CreateFootballClubComponent";

import ViewFootBallClubComponent from "./components/ViewFootBallClubComponent";
import MatchesStatisticsComponent from "./components/MatchesStatisticsComponent";
import MatchesStatisticsByDateComponent from "./components/MatchesStatisticsByDateComponent";

function App() {
  return (
    <div>
      <Router>
        <HeaderComponent />
        <div className="container">
          <Switch>
            <Route path="/" exact component={ListFootballClubComponent}>

            </Route>
            <Route
              path="/footballClubs"
              component={ListFootballClubComponent}
            ></Route>
            <Route
              path="/add-footballclub/:id"
              component={CreateFootballClubComponent}
            ></Route>
            <Route
              path="/view-footballclub/:id"
              component={ViewFootBallClubComponent}
            ></Route>

            <Route
              path="/matchesStatistics"
              component={MatchesStatisticsComponent}
            ></Route>

            <Route
              path="/matchesStatisticsByDate"
              component={MatchesStatisticsByDateComponent}
            ></Route>

          </Switch>
        </div>
        <FooterComponent />
      </Router>
    </div>
  );
}

export default App;
