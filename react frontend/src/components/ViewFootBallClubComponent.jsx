import React, { Component } from 'react'
import FootballClubService from '../services/FootballClubService'

class ViewFootBallClubComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            footballClub: {}
        }
    }

    componentDidMount() {

        FootballClubService.getFootballClubById(this.state.id).then((res) => {
            this.setState({
                footballClub: res.data
            });
        });
    }

    cancel() {
        this.props.history.push('/footballClubs');
    }

    render() {
        let { footballClub } = this.state;
        return (
            <div>
                <br></br>
                <div className="card col-md-6 offset-md-3">
                    <h3 className="text-center"> View Football Club Details</h3>
                    <div className="card-body">

                        <table className="table">

                            <tbody>
                                <tr>
                                    <td>Club Id:</td>
                                    <td>{footballClub.clubId}</td>
                                </tr>
                                <tr>
                                    <td>Club Name:</td>
                                    <td>{footballClub.name}</td>
                                </tr>
                                <tr>
                                    <td>Club Location:</td>
                                    <td>{footballClub.location}</td>
                                </tr>
                                <tr>
                                    <td>Points:</td>
                                    <td>{footballClub.points}</td>
                                </tr>
                                <tr>
                                    <td>Total Played matches:</td>
                                    <td>{footballClub.noOfMatches}</td>
                                </tr>
                                <tr>
                                    <td>Total wins:</td>
                                    <td>{footballClub.wins}</td>
                                </tr>
                                <tr>
                                    <td>Total loses:</td>
                                    <td>{footballClub.loses}</td>
                                </tr>
                                <tr>
                                    <td>Total draws:</td>
                                    <td>{footballClub.draws}</td>
                                </tr>

                            </tbody>
                        </table>
                        <div className="row">
                            <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{ marginLeft: "30px", marginTop: "10px" }}> Back</button>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default ViewFootBallClubComponent
