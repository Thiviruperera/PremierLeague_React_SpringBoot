import React, { Component } from 'react'

import MatchService from '../services/MatchService'
class MatchesStatisticsByDateComponent extends Component {
  constructor(props) {
    super(props)

    this.state = {
      matchesStatisticsList: [],
      filteredMatches: [],
      date: ``
      //${new Date().getFullYear()}-${`${new Date().getMonth() + 1}`.padStart(2, 0)}-${`${new Date().getDay() + 1}`.padStart(2, 0)}
    }
  }

  componentDidMount() {
    MatchService.getMatchesStatistics().then((res) => {
      this.setState({ matchesStatisticsList: res.data, filteredMatches: res.data });
    });
  }

  getMatches() {

    var selectedDate = this.state.date;

    if (selectedDate === '') {
      this.setState({ filteredMatches: this.state.matchesStatisticsList });
    }
    else {
      var newArraymatchesStatisticsList = this.state.matchesStatisticsList.filter(function (el) {
        return el.timestamp.split(' ')[0] === selectedDate
      });
      this.setState({ filteredMatches: newArraymatchesStatisticsList });

    }
  }

  render() {

    let { filteredMatches, date } = this.state;


    return (
      <div>


        <h2 className="text-center">Matches Statistics By Date</h2>

        <div className="row">

          <input id="datetime" type="date" value={date} onChange={e => this.setState({ date: e.target.value })} />
          <button style={{ marginLeft: "10px" }} onClick={() => this.getMatches()} className="btn btn-info">View </button>

        </div>


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
                    filteredMatches.map(
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

export default MatchesStatisticsByDateComponent
