import React, { Component } from 'react'
import FootballClubService from '../services/FootballClubService'
import MatchService from '../services/MatchService'
import $ from 'jquery'

class ListFootballClubComponent extends Component {
  constructor(props) {
    super(props)

    this.state = {
      footballClubList: [],
      matchDetails: ''
    }
    this.addFootballClub = this.addFootballClub.bind(this);
    this.editFootballClub = this.editFootballClub.bind(this);
    this.deleteFootballClub = this.deleteFootballClub.bind(this);
    this.playMatch = this.playMatch.bind(this);
  }

  deleteFootballClub(id) {
    FootballClubService.deleteFootballClub(id).then(res => {
      this.setState({ footballClubList: this.state.footballClubList.filter(footballClub => footballClub.clubId !== id) });
    });
  }
  viewFootballClub(id) {
    this.props.history.push(`/view-footballclub/${id}`);
  }
  editFootballClub(id) {
    this.props.history.push(`/add-footballclub/${id}`);
  }

  componentDidMount() {
    FootballClubService.getAllFootballClub().then((res) => {
      this.setState({ footballClubList: res.data });
    });
  }

  addFootballClub() {
    this.props.history.push('/add-footballclub/_add');
  }

  playMatch() {
    MatchService.playMatch().then((res) => {
      this.setState({ matchDetails: res.data });

      $(".modal").modal("show");
      FootballClubService.getAllFootballClub().then((res) => {
        this.setState({ footballClubList: res.data });
      });
    });
  }

  sorting(option) {

    let sortedfootballClubList = [];
    if (option === 'points') {

      sortedfootballClubList = this.state.footballClubList.sort((a, b) => {
        return parseInt(b.points) - parseInt(a.points);
      })

    }
    else if (option === 'goals') {
      sortedfootballClubList = this.state.footballClubList.sort((a, b) => {
        return parseInt(b.noOfGoals) - parseInt(a.noOfGoals);
      })
    }
    else {
      sortedfootballClubList = this.state.footballClubList.sort((a, b) => {
        return parseInt(b.wins) - parseInt(a.wins);
      })
    }
    this.setState({ footballClubList: sortedfootballClubList });
  }




  render() {

    let { footballClubList, matchDetails } = this.state;
    return (
      <div>


        <h2 className="text-center">Football Club Points</h2>

        <div className="row">
          <button className="btn btn-primary" onClick={this.addFootballClub}> Add Football Club</button>
          <button className="btn btn-info" style={{ marginLeft: "10px" }} onClick={this.playMatch}> Play a match</button>


          <button className="btn btn-info" style={{ marginLeft: "482px", background: '#8e83c9' }} onClick={(e) => {
            e.preventDefault();
            this.sorting('points');
          }}
          > Sort By Points</button>
          <button className="btn btn-info" style={{ marginLeft: "10px", background: '#8e83c9' }} onClick={(e) => {
            e.preventDefault();
            this.sorting('goals');
          }}> Sort By Goals</button>
          <button className="btn btn-info" style={{ marginLeft: "10px", background: '#8e83c9' }} onClick={(e) => {
            e.preventDefault();
            this.sorting('wins');
          }}> Sort By Wins</button>
        </div>
        <br></br>
        <div className="row">
          <div className="table-wrapper">
            <table className="table table-striped table-bordered table-hover">

              <thead>
                <tr>
                  <th>Club Id</th>
                  <th>Name</th>
                  <th>Points</th>
                  <th>Goals</th>
                  <th>Matches</th>
                  <th>Wins</th>
                  <th>Losses</th>
                  <th>Draws</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {
                  footballClubList.map(
                    footballClub =>
                      <tr key={footballClub.clubId}>
                        <td> {footballClub.clubId} </td>
                        <td> {footballClub.name} </td>
                        <td> {footballClub.points}</td>
                        <td> {footballClub.noOfGoals}</td>
                        <td> {footballClub.noOfMatches}</td>
                        <td> {footballClub.wins}</td>
                        <td> {footballClub.loses}</td>
                        <td> {footballClub.draws}</td>
                        <td>
                          <button onClick={() => this.editFootballClub(footballClub.clubId)} className="btn btn-info">Update </button>
                          <button style={{ marginLeft: "10px" }} onClick={() => this.deleteFootballClub(footballClub.clubId)} className="btn btn-danger">Delete </button>
                          <button style={{ marginLeft: "10px" }} onClick={() => this.viewFootballClub(footballClub.clubId)} className="btn btn-info">View </button>
                        </td>
                      </tr>
                  )
                }
              </tbody>
            </table>
          </div>
        </div>



        <div className="modal fade show in" id="myModal">
          <div className="modal-dialog">
            <div className="modal-content">

              <div className="modal-header">
                <h4 className="modal-title">Match Results</h4>
                <button type="button" className="close" data-dismiss="modal">&times;</button>
              </div>

              <div className="modal-body">
                {matchDetails}

              </div>

              <div className="modal-footer">
                <button type="button" className="btn btn-info" data-dismiss="modal">Close</button>
              </div>

            </div>
          </div>
        </div>
      </div>

    )
  }
}

export default ListFootballClubComponent
