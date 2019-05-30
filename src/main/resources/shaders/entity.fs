#version 150

out vec4 outputColor;
in vec3 positionsOut;

void main() {

    outputColor = vec4(positionsOut * 2, 1);

}
