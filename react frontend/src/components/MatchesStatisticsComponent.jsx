import React, { Component } from 'react'

import MatchService from '../services/MatchService'


class MatchesStatisticsComponent extends Component {
  constructor(props) {
    super(props)

    this.state = {
      matchesStatisticsList: []
    }
  }

  componentDidMount() {
    MatchService.getMatchesStatistics().then((res) => {
      this.setState({ matchesStatisticsList: res.data });
    });
  }

  render() {

    let { matchesStatisticsList } = this.state;
    return (
      <div>


        <h2 className="text-center">Matches Statistics</h2>



        <br></br>
        <div className="row">
          <div className="col-xs-12">
            <div className="table-wrapper">
              <table className="table table-striped table-bordered table-hover">

                <thead>
                  <tr>
                    <th>Match Id</th>
                    <th>Club 1</th>
                    <th>Score</th>
                    <th>Club 2</th>
                    <th>Score</th>
                    <th>Winner</th>
                    <th>Date/Time</th>


                  </tr>
                </thead>
                <tbody>
                  {
                    matchesStatisticsList.map(
                      matchesStatistics =>
                        <tr key={matchesStatistics.matchId}>
                          <td> {matchesStatistics.matchId} </td>
                          <td> {matchesStatistics.teamAName} </td>
                          <td> {matchesStatistics.teamAScore}</td>
                          <td> {matchesStatistics.teamBName}</td>
                          <td> {matchesStatistics.teamBScore}</td>
                          <td> {matchesStatistics.winningTeamName}</td>
                          <td> {matchesStatistics.timestamp}</td>
                        </tr>
                    )
                  }
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>

    )
  }
}

export default MatchesStatisticsComponent
