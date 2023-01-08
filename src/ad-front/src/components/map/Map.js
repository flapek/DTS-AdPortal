import React from 'react'
import {GoogleMap, Marker, useJsApiLoader} from '@react-google-maps/api';

const containerStyle = {
    height: 480,
    width: "97.5%"
};

const  MapToEdit = props => {
    const {isLoaded} = useJsApiLoader({
        id: 'google-map-script',
        googleMapsApiKey: process.env.NEXT_PUBLIC_GOOGLE_MAPS_API_KEY
    })

    const [map, setMap] = React.useState(null)

    const [center, setCenter] = React.useState({
        lat: 52.227716,
        lng: 21.002394
    })

    const [position, setPosition] = React.useState(null)

    const onLoad = React.useCallback(function callback(map) {
        const bounds = new window.google.maps.LatLngBounds(center);
        map.fitBounds(bounds);
        setMap(map)
    }, [])

    const onUnmount = React.useCallback(function callback(map) {
        setMap(null)
    }, [])

    return isLoaded ? (
        <GoogleMap
            mapContainerStyle={containerStyle}
            center={center}
            zoom={5}
            onLoad={onLoad}
            onUnmount={onUnmount}
            onClick={(e => {
                setPosition({
                    lat: e.latLng.lat(),
                    lng: e.latLng.lng()
                });
                props.addPosition({
                    lat: e.latLng.lat(),
                    lon: e.latLng.lng()
                })
            })}
        >
            <Marker position={position}
            />
        </GoogleMap>
    ) : <></>
}

export default React.memo(MapToEdit)