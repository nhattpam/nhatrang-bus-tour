class Station {
  int? stationId;
  String? stationName;
  String? stationLocation;

  Station({this.stationId, this.stationName, this.stationLocation});

  Station.fromJson(Map<String, dynamic> json) {
    stationId = json['stationId'];
    stationName = json['stationName'];
    stationLocation = json['stationLocation'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['stationId'] = this.stationId;
    data['stationName'] = this.stationName;
    data['stationLocation'] = this.stationLocation;
    return data;
  }
}