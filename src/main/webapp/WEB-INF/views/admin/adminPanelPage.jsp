<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <common:page pageTitle="Admin panel" showMenu="false">
            <div class="row mb-3">
                <common:back />
            </div>
            <div class="row justify-content-center">
                <h1 class="text-center"><i class="me-2"></i>User Information</h1>

                <div class="table-responsive">
                    <table class="table table-hover table-striped">
                        <thead>
                            <tr>
                                <th><i class="me-1"></i> Login</th>
                                <th><i class="me-1"></i> Avatar</th>
                                <th><i class=" me-1"></i> Location</th>
                                <th><i class="me-1"></i> Company</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>john_doe</td>
                                <td><img src="https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?ixlib=rb-1.2.1&auto=format&fit=crop&w=100&q=80"
                                        class="avatar" alt="John Doe"></td>
                                <td>New York, USA</td>
                                <td>Tech Solutions Inc.</td>
                            </tr>
                            <tr>
                                <td>sara_smith</td>
                                <td><img src="https://images.unsplash.com/photo-1580489944761-15a19d654956?ixlib=rb-1.2.1&auto=format&fit=crop&w=100&q=80"
                                        class="avatar" alt="Sara Smith"></td>
                                <td>London, UK</td>
                                <td>Web Innovations Ltd.</td>
                            </tr>
                            <tr>
                                <td>mike_jones</td>
                                <td><img src="https://images.unsplash.com/photo-1560250097-0b93528c311a?ixlib=rb-1.2.1&auto=format&fit=crop&w=100&q=80"
                                        class="avatar" alt="Mike Jones"></td>
                                <td>Berlin, Germany</td>
                                <td>Data Systems GmbH</td>
                            </tr>
                            <tr>
                                <td>anna_kim</td>
                                <td><img src="https://images.unsplash.com/photo-1544005313-94ddf0286df2?ixlib=rb-1.2.1&auto=format&fit=crop&w=100&q=80"
                                        class="avatar" alt="Anna Kim"></td>
                                <td>Seoul, South Korea</td>
                                <td>Global Tech</td>
                            </tr>
                            <tr>
                                <td>carlos_santos</td>
                                <td><img src="https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?ixlib=rb-1.2.1&auto=format&fit=crop&w=100&q=80"
                                        class="avatar" alt="Carlos Santos"></td>
                                <td>São Paulo, Brazil</td>
                                <td>SouthDev</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </common:page>