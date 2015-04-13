<!--
  Created by IntelliJ IDEA.
  User: FunkySoya
  Date: 2015/3/31
  Time: 22:22
  To change this template use File | Settings | File Templates.
-->
<html>
<head>
  <title></title>
  <link href="resources/css/bootstrap.min.css" rel="stylesheet">
  <style type="text/css">
    .inactive {
      border: none;
      background-color: #fff;
    }
    .active{
      background-color: #fff;
    }
  </style>
</head>

<body ng-app="app" ng-controller="CountryListCtrl">
  <div class="container" >
    <div class="row">
      <div class="col-md-12">
        <table class="table table-bordered table-hover">
          <thead>
            <tr>
              <th>Code</th>
              <th>Name</th>
              <th>Name</th>
              <th>Continent</th>
              <th>Region</th>
              <th>SurfaceArea</th>
              <th>GovernmentForm</th>
              <th>HeadOfStat</th>
              <th>Capital</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            <tr ng-repeat="country in countries">
              <td>{{country.code}}</td>
              <td><input type="text" id='txt_name_{{country.code}}' ng-model="country.name" class="inactive" readonly /></td>
              <td>{{country.name}}</td>
              <td>{{country.continent}}</td>
              <td>{{country.region}}</td>
              <td>{{country.surfaceArea}}</td>
              <td>{{country.governmentForm}}</td>
              <td>{{country.headOfState}}</td>
              <td>{{country.capital}}</td>
              <td>
                <edit ng-Model="country" ng-show="showEdit"><a>Edit</a></edit>
                <update ng-Model="country" ng-show="!showEdit"><a>Update</a></update>
                <cancel ng-Model="country" ng-show="!showEdit"><a>Cancel</a></cancel>
                <delete ng-Model="country"><a>Delete</a></delete>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>

  <script type="text/javascript" src="/resources/js/angular.js"></script>
  <script type="text/javascript" src="/resources/js/jquery-2.1.3.min.js"></script>
  <script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="/resources/js/tdct/countryviewer.js"></script>
</body>
</html>
