var app = angular.module('app', ['ngResource', 'ngGrid', 'ui.bootstrap']);

app.controller('searchController', function ($scope, $rootScope, realmsService) {
    realmsService.query(function (data) {
        $scope.realms = data;
    });

    $scope.submit = function () {
        $rootScope.$broadcast('refreshGrid', $scope.search);
    };

    $scope.formatRealm = function($model) {
        var inputLabel = '';

        angular.forEach($scope.realms, function(realm) {
            if ($model === realm.id) {
                inputLabel = realm.realmDetail;
            }
        });

        return inputLabel;
    }
});

app.controller('itemsController', function ($scope, itemsService) {
    $scope.gridOptions = {
        data: 'itemData',
        useExternalSorting: false,
        multiSelect: false,
        selectedItems: [],

        columnDefs: [
            { field: 'quantity', displayName: 'Quantity' },
            { field: 'bid', displayName: 'Bid' },
            { field: 'minBid', displayName: 'Min Bid' },
            { field: 'maxBid', displayName: 'Max Bid' },
            { field: 'avgBid', displayName: 'Avg Bid' },
            { field: 'buyout', displayName: 'Buyout' },
            { field: 'minBuyout', displayName: 'Min Buyout' },
            { field: 'maxBuyout', displayName: 'Max Buyout' },
            { field: 'avgBuyout', displayName: 'Avg Buyout' },
            { field: 'auctionHouse', displayName: 'AH' },
        ]
    };

    $scope.$on('refreshGrid', function (event, id) {
        itemsService.query(id, function (data) {
            $scope.itemData = data;
            $scope.itemId = id.itemId;

            document.getElementById("itemLink").href = "http://www.wowhead.com/item=" + $scope.itemId;

            $WowheadPower.refreshLinks();
        })
    });
});

app.factory('realmsService', function ($resource) {
    return $resource('resources/wowauctions/realms');
});

app.factory('itemsService', function ($resource) {
    return $resource('resources/wowauctions/items');
});
