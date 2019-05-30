#version 150

in vec3 positionsIn;

out vec3 positionsOut;

uniform vec3 offset;

void main() {
    positionsOut = positionsIn + offset;
    gl_Position = vec4(positionsOut, 1);
}