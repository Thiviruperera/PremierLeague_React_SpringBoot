import React, { Component } from 'react'
import FootballClubService from '../services/FootballClubService';

class CreateFootballClubComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            clubName: '',
            clubLocation: ''

        }
        this.changeClubNameHandler = this.changeClubNameHandler.bind(this);
        this.changeClubLocationHandler = this.changeClubLocationHandler.bind(this);
        this.saveOrUpdateClub = this.saveOrUpdateClub.bind(this);
    }

    componentDidMount() {

        if (this.state.id === '_add') {
            return
        } else {
            FootballClubService.getFootballClubById(this.state.id).then((res) => {
                let footballClub = res.data;
                this.setState({
                    clubName: footballClub.name,
                    clubLocation: footballClub.location
                });
            });
        }
    }
    saveOrUpdateClub = (e) => {
        e.preventDefault();
        let footballClub = { name: this.state.clubName, location: this.state.clubLocation };
        console.log('footballClub => ' + JSON.stringify(footballClub));

        if (this.state.id === '_add') {
            FootballClubService.createFootballClub(footballClub).then(res => {
                this.props.history.push('/footballClubs');
            });
        } else {
            FootballClubService.updateFootballClub(footballClub, this.state.id).then(res => {
                this.props.history.push('/footballClubs');
            });
        }
    }

    changeClubNameHandler = (event) => {
        this.setState({ clubName: event.target.value });
    }

    changeClubLocationHandler = (event) => {
        this.setState({ clubLocation: event.target.value });
    }

    cancel() {
        this.props.history.push('/footballClubs');
    }

    getTitle() {
        if (this.state.id === '_add') {
            return <h3 className="text-center">Add Football Club</h3>
        } else {
            return <h3 className="text-center">Update Football Club</h3>
        }
    }
    render() {
        return (
            <div>
                <br></br>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            {
                                this.getTitle()
                            }
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label> Club Name: </label>
                                        <input placeholder="Club Name" name="clubName" className="form-control"
                                            value={this.state.clubName} onChange={this.changeClubNameHandler} />
                                    </div>
                                    <div className="form-group">
                                        <label> Club Location: </label>
                                        <input placeholder="Club Location" name="clubLocation" className="form-control"
                                            value={this.state.clubLocation} onChange={this.changeClubLocationHandler} />
                                    </div>

                                    <button className="btn btn-success" onClick={this.saveOrUpdateClub}>Save</button>
                                    <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{ marginLeft: "10px" }}>Cancel</button>
                                </form>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        )
    }
}

export default CreateFootballClubComponent
