DESCRIPTION = "Scripting tools for the BeagleBoard and BeagleBone"

PR = "r18"

inherit systemd

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f02920251cbdc9b014dc1cbdb2bb95c4"

SRCREV = "a44ad17edaaff04c92b0204aa6625e9d6a9e4368"

SRC_URI = "git://github.com/jadonk/bonescript.git"

S = "${WORKDIR}/bonescript"

do_install() {
	install -d ${D}/node_modules/bonescript
	cp -a ${S}/node_modules/bonescript/* ${D}/node_modules/bonescript

	install -d ${D}${base_libdir}/systemd/system
	install -m 0644 ${S}/systemd/bonescript.service ${D}${base_libdir}/systemd/system
	install -m 0644 ${S}/systemd/bonescript.socket ${D}${base_libdir}/systemd/system
}

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "bonescript.socket"

FILES_${PN} += "/node_modules/bonescript ${base_libdir}/systemd/system"
RDEPENDS_${PN} = "nodejs beaglebone-getting-started"

FILES_${PN}-dbg += "/node_modules/bonescript/build/Release/.debug"
